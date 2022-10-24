/***************************************************************************
 *  Copyright (C) 2012 by H-Store Project                                  *
 *  Brown University                                                       *
 *  Massachusetts Institute of Technology                                  *
 *  Yale University                                                        *
 *                                                                         *
 *  Alex Kalinin (akalinin@cs.brown.edu)                                   *
 *  http://www.cs.brown.edu/~akalinin/                                     *
 *                                                                         *
 *  Permission is hereby granted, free of charge, to any person obtaining  *
 *  a copy of this software and associated documentation files (the        *
 *  "Software"), to deal in the Software without restriction, including    *
 *  without limitation the rights to use, copy, modify, merge, publish,    *
 *  distribute, sublicense, and/or sell copies of the Software, and to     *
 *  permit persons to whom the Software is furnished to do so, subject to  *
 *  the following conditions:                                              *
 *                                                                         *
 *  The above copyright notice and this permission notice shall be         *
 *  included in all copies or substantial portions of the Software.        *
 *                                                                         *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,        *
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF     *
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. *
 *  IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR      *
 *  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,  *
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR  *
 *  OTHER DEALINGS IN THE SOFTWARE.                                        *
 ***************************************************************************/

package com.oltpbenchmark.benchmarks.tpce.tablegenerator;

import com.oltpbenchmark.benchmarks.tpce.TPCEGenerator;
import com.oltpbenchmark.benchmarks.tpce.fileparser.InputFileHandler;


public class StatusTypeGenerator extends TableGenerator {
    public enum StatusTypeId {
        E_COMPLETED,
        E_ACTIVE,
        E_SUBMITTED,
        E_PENDING,
        E_CANCELED;

    }

    private static final int columnsNum = 2;
    private final InputFileHandler st_file;
    private int counter = 0;
    private final int table_size;

    public StatusTypeGenerator(TPCEGenerator generator) {
        super(generator);

        st_file = generator.getInputFile(TPCEGenerator.InputFile.STATUS);
        table_size = st_file.getRecordsNum();
    }

    @Override
    public boolean hasNext() {
        return counter < table_size;
    }

    @Override
    public Object[] next() {
        Object tuple[] = new Object[columnsNum];
        String st_record[] = st_file.getTupleByIndex(counter++);
        int col = 0;

        tuple[col++] = st_record[0]; // st_id
        tuple[col++] = st_record[1]; // st_name

        return tuple;
    }
}
