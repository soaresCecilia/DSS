package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;



import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ArquivoDAO extends Map<Integer, Arquivo> {

    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object key);

    public boolean containsValue(Object value);

    public Arquivo get(Object key);

    public Arquivo put(Integer key, Arquivo value);

    public Arquivo remove(Object key);

    public void putAll(Map<? extends Integer, ? extends Arquivo> m);

    public void clear();

    public Set<Integer> keySet();

    public Collection<Arquivo> values();

    public Set<Entry<Integer, Arquivo>> entrySet();

    public int proximoId();

}