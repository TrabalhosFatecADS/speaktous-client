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

        if (operacao == 1) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setUser(user);
            pessoa.setPasswd(passwd);
            pessoa.setEmail(email);
            pessoa.setDtcadastro(dtcadastro);
            try {
                sendPost(pessoa);
            } catch (MinhaException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    private String post(Pessoa pessoa) throws IOException {

        URL url = new URL("http://192.168.102.102:8080/pessoa/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        connection.setDoOutput(true);

        //StringBuilder resposta = new StringBuilder();
        Gson gson = new Gson();

        String strPessoa = gson.toJson(pessoa, Pessoa.class);    //fromJson(pessoa.toString(),Pessoa.class);

        //PrintStream printStream = new PrintStream(connection.getOutputStream());
        //printStream.println(strPessoa);
        byte[] postDataBytes = strPessoa.toString().getBytes("UTF-8");
        connection.getOutputStream().write(postDataBytes);
        connection.connect();

        return new Scanner(connection.getInputStream()).next();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String sendPost(Pessoa pessoa) throws MinhaException {

        try {
            // Cria um objeto HttpURLConnection:
            HttpURLConnection request = (HttpURLConnection) new URL("http://192.168.0.17:8080/pessoa/").openConnection();

            try {
                // Define que a conexão pode enviar informações e obtê-las de volta:
                request.setDoOutput(true);
                request.setDoInput(true);

                // Define o content-type:
                request.setRequestProperty("Content-Type", "application/json");

                // Define o método da requisição:
                request.setRequestMethod("POST");

                // Conecta na URL:
                request.connect();

                Gson gson = new Gson();
                // Escreve o objeto JSON usando o OutputStream da requisição:
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(gson.toJson(pessoa, Pessoa.class).getBytes("UTF-8"));
                }

                // Caso você queira usar o código HTTP para fazer alguma coisa, descomente esta linha.
                //int response = request.getResponseCode();

                return readResponse(request);
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