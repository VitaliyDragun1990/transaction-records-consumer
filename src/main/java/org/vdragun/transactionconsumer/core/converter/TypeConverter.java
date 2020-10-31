package org.vdragun.transactionconsumer.core.converter;

public interface TypeConverter<S, T> {

    T convert(S source);
}
