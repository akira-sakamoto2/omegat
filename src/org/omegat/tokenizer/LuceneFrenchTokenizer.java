/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.
 
 Copyright (C) 2008 Alex Buloichik (alex73mail@gmail.com)
               2013 Aaron Madlon-Kay
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/
package org.omegat.tokenizer;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

/**
 * @author Alex Buloichik (alex73mail@gmail.com)
 * @author Aaron Madlon-Kay
 */
@Tokenizer(languages = { "fr" }, isDefault = true)
public class LuceneFrenchTokenizer extends BaseTokenizer {

    @Override
    public Map<Version, String> getSupportedBehaviors() {
        Map<Version, String> result = new LinkedHashMap<Version, String>();
        result.putAll(super.getSupportedBehaviors());
        result.put(Version.LUCENE_36, result.get(Version.LUCENE_36) + " (UniNE)");
        result.put(Version.LUCENE_31, result.get(Version.LUCENE_31) + " (Snowball)");
        result.put(Version.LUCENE_20, result.get(Version.LUCENE_20) + " (Porter)");
        return result;
    }

    @Override
    protected TokenStream getTokenStream(final String strOrig,
            final boolean stemsAllowed, final boolean stopWordsAllowed) {
        if (stemsAllowed) {
            String[] stopWords = stopWordsAllowed ? FrenchAnalyzer.FRENCH_STOP_WORDS
                    : EMPTY_STOP_WORDS_LIST;
            return new FrenchAnalyzer(getBehavior(), stopWords).tokenStream("", new StringReader(
                    strOrig));
        } else {
            return new StandardTokenizer(getBehavior(),
                    new StringReader(strOrig.toLowerCase()));
        }
    }
}
