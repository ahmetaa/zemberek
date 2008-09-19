/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.tr.yapi.ek;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.zemberek.tr.yapi.TurkceSesliUretici;
import net.zemberek.yapi.Alfabe;
import net.zemberek.yapi.HarfDizisi;
import net.zemberek.yapi.TurkceHarf;
import net.zemberek.yapi.ek.EkUretici;
import net.zemberek.yapi.ek.EkUretimBileseni;
import net.zemberek.yapi.ek.TemelEkUretici;
import net.zemberek.yapi.ek.TemelEkUretimKurali;


public class EkUreticiTr extends TemelEkUretici implements EkUretici {

    private TurkceSesliUretici sesliUretici;
    public final TurkceHarf HARF_a;
    public final TurkceHarf HARF_e;
    public final TurkceHarf HARF_i;
    public final TurkceHarf HARF_ii;
    public final TurkceHarf HARF_u;
    public final TurkceHarf HARF_uu;

    public EkUreticiTr(Alfabe alfabe) {
        this.sesliUretici = new TurkceSesliUretici(alfabe);
        HARF_a = alfabe.harf('a');
        HARF_e = alfabe.harf('e');
        HARF_i = alfabe.harf('i');
        HARF_ii = alfabe.harf(Alfabe.CHAR_ii);
        HARF_u = alfabe.harf('u');
        HARF_uu = alfabe.harf(Alfabe.CHAR_uu);
    }

    public HarfDizisi cozumlemeIcinEkUret(HarfDizisi ulanacak, HarfDizisi giris, List<EkUretimBileseni> bilesenler) {
        HarfDizisi sonuc = new HarfDizisi(4);
        TurkceHarf sonSesli = ulanacak.sonSesli();
        for (int i = 0; i < bilesenler.size(); i++) {
            EkUretimBileseni ekUretimBileseni = bilesenler.get(i);
            final TurkceHarf harf = ekUretimBileseni.harf;
            switch ((TemelEkUretimKurali) ekUretimBileseni.kural) {
                case HARF:
                    sonuc.ekle(harf);
                    break;
                case KAYNASTIR:
                    if (ulanacak.sonHarf().sesliMi())
                        sonuc.ekle(harf);
                    break;
                case SERTLESTIR:
                    if (ulanacak.sonHarf().sertMi())
                        sonuc.ekle(harf.sertDonusum());
                    else
                        sonuc.ekle(harf);
                    break;
                case SESLI_AE:
                    if (i == 0 && ulanacak.sonHarf().sesliMi())
                        break;
                    else {
                        sonSesli = sesliUretici.sesliBelirleAE(sonSesli);
                        sonuc.ekle(sonSesli);
                    }
                    break;
                case SESLI_IU:
                    if (i == 0 && ulanacak.sonHarf().sesliMi())
                        break;
                    else {
                        sonSesli = sesliUretici.sesliBelirleIU(sonSesli);
                        sonuc.ekle(sonSesli);
                    }
                    break;
            }
        }
        return sonuc;
    }

    @Override
    public Set<TurkceHarf> olasiBaslangicHarfleri(List<EkUretimBileseni> bilesenler) {
        Set<TurkceHarf> kume = new HashSet<TurkceHarf>(4);
        for (int i = 0; i < bilesenler.size(); i++) {
            EkUretimBileseni bilesen = bilesenler.get(i);
            final TurkceHarf harf = bilesen.harf;
            switch ((TemelEkUretimKurali) bilesen.kural) {
                case HARF:
                    kume.add(harf);
                    return kume;
                case KAYNASTIR:
                    kume.add(harf);
                    break;
                case SERTLESTIR:
                    kume.add(harf);
                    kume.add(harf.sertDonusum());
                    return kume;
                case SESLI_AE:
                    kume.add(HARF_a);
                    kume.add(HARF_e);
                    if (i > 0)
                        return kume;
                case SESLI_IU:
                    kume.add(HARF_i);
                    kume.add(HARF_u);
                    kume.add(HARF_ii);
                    kume.add(HARF_uu);
                    if (i > 0)
                        return kume;
            }
        }
        return kume;
    }


}