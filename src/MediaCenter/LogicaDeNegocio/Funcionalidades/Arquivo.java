package MediaCenter.LogicaDeNegocio.Funcionalidades;

import java.util.List;

public interface Arquivo {

    public String getNome();

    public String getTipoArq();

    public int getID();

    public String getCaminho();

    public String getAutor();

    public String getAlbum_Temporada();

    public String getCategoria();

    public List<Object> arquivoToLinha(Arquivo arq);

    public int chave();
}