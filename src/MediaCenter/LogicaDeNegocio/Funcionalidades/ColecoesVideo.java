package MediaCenter.LogicaDeNegocio.Funcionalidades;

import java.util.List;

public class ColecoesVideo implements Colecao {

    private List<Integer> idsArquivosUpload;
    private List<Integer> idsArquivosDownload;

    public ColecoesVideo(List<Integer> idsArquivosUpload, List<Integer> idsArquivosDownload) {
        this.idsArquivosUpload = idsArquivosUpload;
        this.idsArquivosDownload = idsArquivosDownload;
    }

    public List<Integer> getIdsArquivosUpload() {
        return idsArquivosUpload;
    }

    public List<Integer> getIdsArquivosDownload() {
        return idsArquivosDownload;
    }

    public void setIdsArquivosUpload(List<Integer> idsArquivosUpload) {
        this.idsArquivosUpload = idsArquivosUpload;
    }

    public void setIdsArquivosDownload(List<Integer> idsArquivosDownload) {
        this.idsArquivosDownload = idsArquivosDownload;
    }

    @Override
    public int tamColecaoUploads() {
        return idsArquivosUpload.size();
    }

    @Override
    public int tamColecaoDownloads() {
        return idsArquivosDownload.size();
    }

}
