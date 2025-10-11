/*
 * Copyright (C) 2008 Júlio Vilmar Gesser.
 * 
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 11/06/2008
 */
package japa.parser.ast.test;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.test.classes.DumperTestClass;
import japa.parser.ast.visitor.GenericVisitorAdapter;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;

import junit.framework.TestCase;

/**
 * @author Julio Vilmar Gesser
 */
public class TestAdapters extends TestCase {

    private String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        try {
            StringBuilder ret = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                ret.append(line);
                ret.append("\n");
            }
            return ret.toString();
        } finally {
            reader.close();
        }
    }

    public void testVoidVisitorAdapter() throws Exception {
        String source = readFile("./test/" + DumperTestClass.class.getName().replace('.', '/') + ".java");
        CompilationUnit cu = JavaParser.parse(new StringBufferInputStream(source));
        cu.accept(new VoidVisitorAdapter(), null);
    }

    public void testGenericVisitorAdapter() throws Exception {
        String source = readFile("./test/" + DumperTestClass.class.getName().replace('.', '/') + ".java");
        CompilationUnit cu = JavaParser.parse(new StringBufferInputStream(source));
        cu.accept(new GenericVisitorAdapter(), null);
    }

}
