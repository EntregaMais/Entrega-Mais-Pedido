package br.com.entrega_mais.pedido.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal frete;

    private Long idTransportadora;

    private BigDecimal valorTotal;

    private String estado;

    private String cidade;

    private int fornPagouFrete;

    private String quemPagaTaxa;

    private String nmCliente;

    private String telefoneCli;

    private String emailCli;

    private String formaPag;

    private String status;

    private Long idVeiculo;

    private Long idDespachante;

}
