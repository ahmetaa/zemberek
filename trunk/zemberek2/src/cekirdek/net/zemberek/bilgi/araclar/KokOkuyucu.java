package net.zemberek.bilgi.araclar;

import net.zemberek.yapi.Kok;

import java.io.IOException;
import java.util.List;


/**
 */
public interface KokOkuyucu {

    List hepsiniOku() throws IOException;

    Kok oku() throws IOException;

}
