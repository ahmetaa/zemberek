/*************************************************************************
 *
 *  $RCSfile: XPossibleHyphens_impl.java,v $
 *
 *  $Revision: 1.1 $
 *
 *  last change: $Author: mdakin $ $Date: 2005/01/11 20:30:03 $
 *
 *  The Contents of this file are made available subject to the terms of
 *  the BSD license.
 *  
 *  Copyright (c) 2003 by Sun Microsystems, Inc.
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of Sun Microsystems, Inc. nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *  FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *  BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 *  OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *  TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 *  USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *     
 *************************************************************************/
package net.zemberek.ooo.hyphenator;

import com.sun.star.lang.Locale;

public class XPossibleHyphens_impl implements
    com.sun.star.linguistic2.XPossibleHyphens
{
    String              aWord;
    String              aHyphWord;
    short[]             aOrigHyphenPos;
    Locale              aLang;

    public XPossibleHyphens_impl(
            String      aWord, 
            Locale      aLang,
            String      aHyphWord,
            short[]     aOrigHyphenPos)
    {
        this.aWord = aWord;
        this.aLang = aLang;
        this.aHyphWord = aHyphWord;
        this.aOrigHyphenPos = aOrigHyphenPos;
    
        //!! none of these cases should ever occur! 
        //!! values provided only for safety
        if (this.aWord == null)
            this.aWord = new String();
        if (this.aLang == null)
            this.aLang = new Locale();
        if (this.aHyphWord == null)
            this.aHyphWord = new String();

        // having no hyphenation positions is OK though.
        // still for safety an empty existing array has to be provided.
        if (this.aOrigHyphenPos == null)
            this.aOrigHyphenPos = new short[]{};
    }
    
    // XPossibleHyphens
    public String getWord() throws com.sun.star.uno.RuntimeException
    {
        return aWord;
    }

    public Locale getLocale() throws com.sun.star.uno.RuntimeException
    {
        return aLang;
    }
    public String getPossibleHyphens() throws com.sun.star.uno.RuntimeException 
    {
        return aHyphWord;            
    }
    public short[] getHyphenationPositions() throws com.sun.star.uno.RuntimeException
    {
        return aOrigHyphenPos;
    }
};
