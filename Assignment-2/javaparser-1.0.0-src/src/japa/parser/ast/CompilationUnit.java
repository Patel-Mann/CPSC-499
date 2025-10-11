/*
 * Copyright (C) 2007 Júlio Vilmar Gesser.
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
 * Created on 05/10/2006
 */
package japa.parser.ast;

import japa.parser.ast.body.AnnotationDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EmptyTypeDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.JavadocComment;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

import java.util.List;

/**
 * <p>This class represents the entire compilation unit. Each java
 * file denotes a compilation unit.</p>
 * 
 * The CompilationUnit is constructed following the syntax:<br>
 * <code>
 * <table>
 * <tr valign=baseline>
 *   <td align=right>CompilationUnit</td>
 *   <td align=center>::=</td>
 *   <td align=left>
 *   ( {@link PackageDeclaration} )?<br> 
 *   ( {@link ImportDeclaration} )*<br>
 *   ( {@link TypeDeclaration} )*<br>
 *   </td>
 * </tr>
 * </table> 
 * </code>
 * 
 * @author Julio Vilmar Gesser
 */
public final class CompilationUnit extends Node {

    private final PackageDeclaration pakage;

    private final List<ImportDeclaration> imports;

    private final List<TypeDeclaration> types;

    private final List<Comment> comments;

    public CompilationUnit(int line, int column, PackageDeclaration pakage, List<ImportDeclaration> imports, List<TypeDeclaration> types, List<Comment> comments) {
        super(line, column);
        this.pakage = pakage;
        this.imports = imports;
        this.types = types;
        this.comments = comments;
    }

    /**
     * Retrieves the package declaration of this compilation unit.<br>
     * If this compilation unit has no package declaration (default package),
     * <code>null</code> is returned.
     * @return the package declaration or <code>null</code>
     */
    public PackageDeclaration getPakage() {
        return pakage;
    }

    /**
     * Retrieves the list of imports declared in this compilation unit or
     * <code>null</code> if there is no import.
     * @return the list of imports or <code>null</code> if there is no import
     */
    public List<ImportDeclaration> getImports() {
        return imports;
    }

    /**
     * Return the list of types declared in this compilation unit.<br>
     * If there is no types declared, <code>null</code> is returned.
     * @return the list of types or <code>null</code> null if there is no type
     * @see AnnotationDeclaration
     * @see ClassOrInterfaceDeclaration
     * @see EmptyTypeDeclaration
     * @see EnumDeclaration
     */
    public List<TypeDeclaration> getTypes() {
        return types;
    }

    /**
     * Return a list containing all comments declared in this compilation unit.
     * Including javadocs, line comments and block comments of all types, 
     * inner-classes and other members.<br>
     * If there is no comment, <code>null</code> is returned.
     * @return list with all comments of this compilation unit of <code>null</code>
     * @see JavadocComment
     * @see LineComment
     * @see BlockComment
     */
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

}
