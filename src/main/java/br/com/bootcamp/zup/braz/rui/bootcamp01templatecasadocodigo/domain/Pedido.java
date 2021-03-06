package br.com.bootcamp.zup.braz.rui.bootcamp01templatecasadocodigo.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @NotNull
    @Valid
    private Compra compra;

    @ElementCollection
    @CollectionTable(name = "t_itens_pedido")
    @Size(min = 1)
    private Set<ItemCompra> itens = new HashSet<>();

    @Deprecated
    public Pedido(){}

    public Pedido(@NotNull @Valid Compra compra, @Size(min = 1)Set<ItemCompra> itens){
        Assert.isTrue(!itens.isEmpty(), "Todo pedido deve ter pelo menos um item.");
        this.compra = compra;
        this.itens.addAll(itens);
    }



    public boolean totalIgual(@Positive @NotNull BigDecimal total) {
                                                    //1                                                                 //1
        BigDecimal totalPedido = itens.stream().map(ItemCompra::valorTotal).reduce(BigDecimal.ZERO, (atual, proximo) -> atual.add(proximo));

        return totalPedido.doubleValue() == total.doubleValue();
    }

    public Integer getId() {
        return id;
    }
}
