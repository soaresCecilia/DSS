package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Colecoes;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ColecoesDAO extends Map<Key, Colecoes> {

    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object key);

    public boolean containsValue(Object value);

    public Colecoes get(Object key);

    public Colecoes put(Key key, Colecoes value);

    public Colecoes remove(Object key);

    public void putAll(Map<? extends Key, ? extends Colecoes> colecao);

    public void clear();

    public Set<Key> keySet();

    public Collection<Colecoes> values();

    public Set<Entry<Key, Colecoes>> entrySet();
}
