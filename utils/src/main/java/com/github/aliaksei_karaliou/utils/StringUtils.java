package com.github.aliaksei_karaliou.utils;

public final class StringUtils {

    public static <T> String glue(final Iterable<T> pIterable, final String pSeparator) {
        final StringBuilder builder = new StringBuilder();

        for (final T t : pIterable) {
            builder.append(t).append(pSeparator);
        }

        return builder.length() > pSeparator.length() ?
                builder.substring(0, builder.length() - pSeparator.length()).trim() :
                "";
    }

    public static <T> String glue(final T[] pArray, final String pSeparator) {
        final StringBuilder builder = new StringBuilder();

        for (final T t : pArray) {
            builder.append(t).append(pSeparator);
        }

        return builder.length() > pSeparator.length() ?
                builder.substring(0, builder.length() - pSeparator.length()).trim() :
                "";
    }
}
