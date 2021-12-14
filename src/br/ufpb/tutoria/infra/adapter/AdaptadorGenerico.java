package br.ufpb.tutoria.infra.adapter;

import java.util.List;
import java.util.SortedSet;

public interface AdaptadorGenerico<S> {
    Object adaptar(String entrada);
}
