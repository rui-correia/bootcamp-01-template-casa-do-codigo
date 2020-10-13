package br.com.bootcamp.zup.braz.rui.bootcamp01templatecasadocodigo.requests;

import br.com.bootcamp.zup.braz.rui.bootcamp01templatecasadocodigo.annotation.ObjetoValido;
import br.com.bootcamp.zup.braz.rui.bootcamp01templatecasadocodigo.domain.Compra;
import br.com.bootcamp.zup.braz.rui.bootcamp01templatecasadocodigo.domain.Estado;
import br.com.bootcamp.zup.braz.rui.bootcamp01templatecasadocodigo.domain.Pais;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class NovaCompraRequest {

    @NotBlank
    @Email(message = "Fomato de email inválido.")
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @Pattern(message = "Formato do documento inválido.", regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ObjetoValido(fieldName = "id", domainClass = Pais.class)
    private Integer idPais;
    private Integer idEstado;
    @NotBlank
    @Pattern(message = "Formato do telefone inválido.", regexp = "^[0-9]{2}-[0-9]{4}-[0-9]{4}$")
    private String telefone;
    @NotBlank
    @Pattern(message = "Formato do CEP inválido.", regexp = "^\\d{5}-\\d{3}$")
    private String cep;

    public NovaCompraRequest(){

    }

    public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Pattern(message = "Formato do documento inválido.", regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})") String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, @NotNull Integer idPais, Integer idEstado, @NotBlank @Pattern(message = "Formato do telefone inválido", regexp = "^[0-9]{2}-[0-9]{4}-[0-9]{4}$") String telefone, @NotBlank @Pattern(message = "Formato do CEP inválido", regexp = "^\\d{5}-\\d{3}$") String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Compra toModel(EntityManager entityManager) {
        @NotNull Pais pais = entityManager.find(Pais.class, idPais);

        Query customQuery = entityManager.createQuery("select 1 from " + Estado.class + " where id_pais = :idPais");
        customQuery.setParameter("idPais", pais.getId());

        List<Estado> estados = customQuery.getResultList();
        Estado estado = null;

        if (!estados.isEmpty()){

        }

        return new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado, telefone, cep);
    }
}