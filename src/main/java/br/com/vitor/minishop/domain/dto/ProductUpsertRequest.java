package br.com.vitor.minishop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpsertRequest {
    @NotBlank(message = "Nome do produto não pode ser vazio.")
    @Size(max = 100, message = "O nome do produto deve ter no máximo 100 caracteres.")
    private String productName;

    @NotNull(message = "Informe um fornecedor válido!")
    private int supplierId;

    @Positive(message = "Informe um preço unitário válido!")
    @NotNull(message = "O preço unitário é obrigatório!")
    private double unitPrice;

    @NotNull(message = "Informe se o produto está Ativo!")
    private Boolean IsDiscontinued;

    @Size(max = 100, message = "O nome do pacote deve ter no máximo 100 caracteres.")
    private String packageName;

    private List<String> urlList;
}
