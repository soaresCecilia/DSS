package MediaCenter.LogicaDeNegocio.Funcionalidades;

import MediaCenter.Dados.ArquivoDAO;

import java.util.*;

public class ColecoesMusica implements Colecoes{

    private List<Integer> idsArquivosUpload;
    private List<Integer> idsArquivosDownload;


    public ColecoesMusica(List<Integer> idsArquivosUpload, List<Integer> idsArquivosDownload) {
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
