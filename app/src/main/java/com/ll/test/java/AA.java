package com.ll.test.java;

/**
 * Created by ll on 16-11-30.
 */

import java.lang.reflect.*;

import java.util.*;


/**
 * @author Cay Horstmann
 * @version 1.10 2007-05-15
 *          <p>
 *          Class类，描述具体类型。
 *          TypeVariable接口，描述类型变量（如T extends Comparable<? super T>）。
 *          WildcardType接口，描述通配符（如? super T）。
 *          ParameterizedType接口，描述泛型类或接口类型（如Comparable<? super T>）。
 *          GenericArrayType接口，描述泛型数组（如T[] p）。
 *          <p>
 *          <p>
 *          <p>
 *          <p>
 *          <p>
 *          <p>
 *          <p>
 *          TypeVariable[] getTypeParameters() 5.0：如果这个类型被声明为泛型类型，则获得泛型类型变量，否则获得一个长度为0的数组。
 *          <p>
 *          Type getGenericSuperclass() 5.0：获得被声明为这一类型的超类的泛型类型，如果这个类型是Object或不是一个类类型，则返回null。
 *          <p>
 *          Type[] getGenericInterfaces() 5.0：获得被声明为这个类型的接口的泛型类型（以声明的次序），否则，如果这个类型没有实现接口，返回长度为0的数组。
 *          <p>
 *          API：java.lang.reflect.Method 1.1
 *          <p>
 *          TypeVariable[] getTypeParameters() 5.0：如果这个方法被声明为泛型方法，则获得泛型类型变量，否则返回长度为0的数组。
 *          <p>
 *          Type getGenericReturnType() 5.0：获得这个方法被声明的泛型返回类型。
 *          <p>
 *          Type[] getGenericParameterTypes() 5.0：获得这个方法被声明的泛型参数类型。如果这个方法没有参数，返回长度为0的数组。
 *          <p>
 *          API：java.lang.reflect.TypeVariable 5.0
 *          <p>
 *          String getName()：获得类型变量的名字。
 *          <p>
 *          Type[] getBounds()：获得类型变量的子类限定，否则，如果该变量无限定，则返回长度为0的数组。
 *          <p>
 *          API：java.lang.reflect.WildcardType 5.0
 *          <p>
 *          Type[] getLowerBounds()：获得这个类型变量的子类（extends）限定，否则，如果没有子类限定，则返回长度为0的数组。
 *          <p>
 *          Type[] getUpperBounds()：获得这个类型变量的超类（super）限定，否则，如果没有超类限定，则返回长度为0的数组。
 *          <p>
 *          API：java.lang.reflect.ParameterType 5.0
 *          <p>
 *          Type getRawType()：获得这个参数化类型的原始类型。
 *          <p>
 *          Type[] getActualTypeArguments()：获得这个参数化类型声明时所使用的类型参数。
 *          <p>
 *          Type getOwnerType()：如果是内部类型，则返回其外部类型，如果是一个顶级类型，则返回null。
 *          <p>
 *          API：java.lang.reflect.GenericArrayType 5.0
 *          <p>
 *          Type getGenericComponentType()：获得声明该数组类型的泛型组合类型。
 */

public class AA {

    public static void main() {

        // read class name from command line args or user input

//        String name = args;

//        if (args.length > 0)

//            name = args[0];

//        else {
//
//            Scanner in = new Scanner(System.in);
//
//            System.out.println("Enter class name (e.g. java.util.Collections): ");
//
//            name = in.next();
//
//        }


//        try {

        // print generic info for class and public methods

//            Class<?> cl = Class.forName(name);
        Class<?> cl = Test_1.class;
        printClass(cl);

        for (Method m : cl.getDeclaredMethods())

            printMethod(m);

//        } catch (ClassNotFoundException e) {
//
//            e.printStackTrace();
//
//        }

    }


    public static void printClass(Class<?> cl) {

        System.out.print(cl + "");

        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);

        Type sc = cl.getGenericSuperclass();

        if (sc != null) {

            System.out.print(" extends ");

            printType(sc, false);

        }

        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);

        System.out.println();

    }


    public static void printMethod(Method m) {

        String name = m.getName();

        System.out.print(Modifier.toString(m.getModifiers()));

        System.out.print(" ");

        printTypes(m.getTypeParameters(), "<", ", ", "> ", true);


        printType(m.getGenericReturnType(), false);

        System.out.print(" ");

        System.out.print(name);

        System.out.print("(");

        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);

        System.out.println(")");

    }


    public static void printTypes(Type[] types, String pre, String sep,

                                  String suf, boolean isDefinition) {

        if (pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class}))

            return;

        if (types.length > 0)

            System.out.print(pre);

        for (int i = 0; i < types.length; i++) {

            if (i > 0)

                System.out.print(sep);

            printType(types[i], isDefinition);

        }

        if (types.length > 0)

            System.out.print(suf);

    }


    public static void printType(Type type, boolean isDefinition) {

        if (type instanceof Class) {

            Class<?> t = (Class<?>) type;

            System.out.print(t.getName());

        } else if (type instanceof TypeVariable) {

            TypeVariable<?> t = (TypeVariable<?>) type;

            System.out.print(t.getName());

            if (isDefinition)

                printTypes(t.getBounds(), " extends ", " & ", "", false);

        } else if (type instanceof WildcardType) {

            WildcardType t = (WildcardType) type;

            System.out.print("?");

            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);

            printTypes(t.getLowerBounds(), " super ", " & ", "", false);

        } else if (type instanceof ParameterizedType) {

            ParameterizedType t = (ParameterizedType) type;

            Type owner = t.getOwnerType();

            if (owner != null) {

                printType(owner, false);

                System.out.print(".");

            }

            printType(t.getRawType(), false);

            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);

        } else if (type instanceof GenericArrayType) {

            GenericArrayType t = (GenericArrayType) type;

            System.out.print("");

            printType(t.getGenericComponentType(), isDefinition);

            System.out.print("[]");

        }

    }

}