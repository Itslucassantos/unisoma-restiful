![Java](https://img.shields.io/static/v1?label=Java&message=8&color=blue)
![Maven](https://img.shields.io/static/v1?label=Maven&message=3.6.3&color=blue)
![SpringBoot](https://img.shields.io/static/v1?label=Spring&message=2.7.12&color=blue)
---
Sobre: projeto onde cadastra um funcionário, calcula seu novo salário quando tem um aumento e quanto vai pagar de IR. 
---
Subindo a aplicação:
1. Rode a classe RestifulApplication
2. A Api estará rodando no http://localhost:8082/api/calculo
3. É um PostMapping para cadastrar e está na url: http://localhost:8082/api/calculo/funcionarios/cadastrar
4. É um GetMapping para listar todos os funcionários e está na url: http://localhost:8082/api/calculo/funcionarios/listar
5. É um PutMapping para calcular um novo salário e está na url: http://localhost:8082/api/calculo/calcularSalarioFuncionario/cpf do funcionário
6. É um GetMapping para calcular o imposto de renda e está na url: http://localhost:8082/api/calculo/calcularImpostoDeRendaFuncionario/cpf do funcionário
---
### Exemplos de valores

####CADASTRAR #1
```bash
url: http://localhost:8082/api/calculo/funcionarios/cadastrar
{
"nome": "Ana Reis",
"cpf": "11559952978",
"dataNascimento": "02/03/1990",
"telefone": "11988779988",
"endereco": "Rua Osvaldo, 270",
"salario": 1400.0
}
```
####URL PARA CALCULAR NOVO SALÁRIO #2
```bash
http://localhost:8082/api/calculo/calcularSalarioFuncionario/11559952978
```
####URL PARA CALCULAR IMPOSTO DE RENDA #3
```bash
http://localhost:8082/api/calculo/calcularImpostoDeRendaFuncionario/11559952978
```
####URL PARA LISTAR TODOS OS FUNCIONÁRIOS #3
```bash
http://localhost:8082/api/calculo/funcionarios/listar
