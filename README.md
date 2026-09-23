# Integração de Pedidos

O pedido é lançado uma vez e chega em vendas, estoque e financeiro.

## O problema

Numa distribuidora pequena, o pedido fechado no WhatsApp é lançado três vezes: o vendedor anota na planilha de vendas, o estoque dá baixa no sistema dele, e o financeiro lança a cobrança na mão.

O resultado é sempre o mesmo. O estoque não bate, o cliente aparece duplicado, a cobrança sai errada ou some. E ninguém sabe o saldo real.

## A ideia

O vendedor lança o pedido num formulário. A automação valida, calcula e grava em três lugares:

- Planilha de vendas no Google Sheets, uma linha por pedido
- Sistema de estoque, baixando cada item
- Sistema financeiro, criando a conta a receber com o vencimento certo

Se alguma etapa falhar, nada fica gravado em lugar nenhum e o responsável é avisado.

A empresa, os produtos e os dados são fictícios. O estoque e o financeiro vão ser APIs que eu mesmo escrevo, representando os sistemas que uma empresa usaria de verdade.

## Stack

n8n para orquestrar, Google Sheets API para a planilha, duas APIs próprias para estoque e financeiro, Docker Compose para subir tudo. Formulário em HTML, CSS e JavaScript, sem framework.

## Próximos passos

- [ ] docker-compose com os serviços
- [ ] API de estoque: catálogo, saldo, baixa e estorno
- [ ] API financeira: contas a receber
- [ ] Planilha e conta de serviço do Google
- [ ] Fluxo no n8n, caminho feliz
- [ ] Rollback e alerta
- [ ] Formulário
- [ ] Roteiro e vídeo da demonstração

## Autor

Sávio Bandeira, automação de processos.
[saviobandeira.com](https://saviobandeira.com) · [LinkedIn](https://www.linkedin.com/in/s%C3%A1vio-bandeira-79760620b/)