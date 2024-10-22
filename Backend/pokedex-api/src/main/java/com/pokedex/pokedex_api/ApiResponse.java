package com.pokedex.pokedex_api;

public class ApiResponse<T> {

    private String message; // Novo campo para a mensagem
    private T data;

    // Construtor
    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
    public ApiResponse(){}

    // Getter para a mensagem
    public String getMessage() {
        return message;
    }

    // Setter para a mensagem
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter para os dados
    public T getData() {
        return data;
    }

    // Setter para os dados
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse [message=" + message + ", data=" + data + "]";
    }

}
