package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Colecao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ColecaoDAO extends Map<Key, Colecao> {

    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object key);

    public boolean containsValue(Object value);

    public Colecao get(Object key);

    public Colecao put(Key key, Colecao value);

    public Colecao remove(Object key);

    public void putAll(Map<? extends Key, ? extends Colecao> colecao);

    public void clear();

    public Set<Key> keySet();

    public Collection<Colecao> values();

    public Set<Entry<Key, Colecao>> entrySet();
}
