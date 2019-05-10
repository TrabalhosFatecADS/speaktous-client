package br.com.douglas.speaktous.service;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import br.com.douglas.speaktous.model.Pessoa;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PessoaService extends AsyncTask<Void,Void, Pessoa> {

    private final String urlBase = "http://192.168.0.17:8080/pessoa/";
    private final Long id;
    private final String nome;
    private final String user;
    private final String passwd;
    private final String email;
    private final String dtcadastro;
    private final int operacao;


    public PessoaService(Long id, String nome, String user, String passwd, String email, String dtcadastro, int operacao) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.passwd = passwd;
        this.email = email;
        this.dtcadastro = dtcadastro;
        this.operacao = operacao;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Pessoa doInBackground(Void... voids) {
        Pessoa pessoa = new Pessoa();
        pessoa.setSenha(passwd);
        pessoa.setEmail(email);
        pessoa.setDtCadastro(dtcadastro);
        if (operacao == 1) {
            try {
                sendPost(pessoa);
            } catch (MinhaException e) {
                e.printStackTrace();
            }
        }
        if(operacao == 2){
            try {
                pessoa = login(pessoa);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pessoa;
    }

    private Pessoa login(Pessoa pessoa) {
        StringBuilder resposta = new StringBuilder();
        Pessoa retorno = new Pessoa();
        try {
            URL url = new URL(urlBase + "login/?email=" + this.email + "&passwd=" + this.passwd );//+ "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                resposta.append(scanner.next());
            }
            Gson gson = new Gson();
            // Staff staff = gson.fromJson(reader, Staff.class);
            retorno = gson.fromJson(resposta.toString(),Pessoa.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return retorno;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int sendPost(Pessoa pessoa) throws MinhaException {

        try {
            HttpURLConnection request = (HttpURLConnection) new URL(urlBase).openConnection();

            try {
                request.setDoOutput(true);
                request.setDoInput(true);

                request.setRequestProperty("Content-Type", "application/json");

                request.setRequestMethod("POST");

                request.connect();

                Gson gson = new Gson();
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(gson.toJson(pessoa, Pessoa.class).getBytes("UTF-8"));
                }

                return request.getResponseCode();
            } finally {
                request.disconnect();
            }
        } catch (IOException ex) {
            throw new MinhaException(ex);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }

    public static class MinhaException extends Exception {
        private static final long serialVersionUID = 1L;

        public MinhaException(Throwable cause) {
            super(cause);
        }
    }
}