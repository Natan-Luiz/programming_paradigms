## Extra 2
Este trabalho permitiu explorar de forma diferenciada a linguagem de programação Java, ao desenvolver programas envolvendo três paradigmas de programação distintos em uma mesma linguagem.

O extra consiste na resolução do problema [COFRE](https://olimpiada.ic.unicamp.br/pratique/p1/2017/f1/cofre/) na linguagem de programação Java, nos paradigmas **procedural**, **funcional** e **orientado a objetos**.

No que se refere a declaração de variáveis, nota-se uma grande diferença entre as três versões desenvolvidas. Enquanto no paradigma procedural declara-se as variáveis de forma independente, no orientado a objetos é criado um objeto que contém os atributos necessários para a resolução do problema. Já no paradigma funcional além de não existir exatamente um conceito de variável, mas sim parâmetros, os quais são passados através das funções, também não há laços de repetição, ao contrário dos demais nos quais esse conceito está muito presente, obrigando, assim, o uso de recursão.

Procedural:
```int n = in.nextInt();
 int m = in.nextInt();
 //[...]
 Integer[] barra = new Integer[n];
 //[...]
 ```

Funcional:
> Não há declaração de variáveis explicitamente!

Orientado a Objetos:
```
public class Dados {
   int n;
   int m;
   Integer[] barra;
   Integer[] sequencia;
   Integer[] aparicoes = new Integer[10];
   //[...]
```

Exemplo de Recursão (Paradigma Funcional):
```
public List<Integer> recursao(int vI, int x, List barra, List ret, List seq) {
if (x == vI) {
return recursao(vI, x - 1, barra, myZipWith(somaUm(0, mai(seq), barra), ret), myRemove(seq, seq.get(0)));
} else if (x > 1) {
return recursao(vI, x - 1, barra, myZipWith(somaUm(men(seq), mai(seq), barra), ret), myRemove(seq, seq.get(0)));
} else {
return ret;
}
}
```

Quanto á organização do código, o paradigma procedural é o mais extenso/"desorganizado". Já no orientado a objetos, o código é dividido em vários arquivos. Por fim, no funcional, há um maior número de funções, visto que cada uma faz uma "tarefa" específica, como exemplifica o trecho de código abaixo:
```
public List<Integer> makeList(int initial, int incremento) {
return Stream.iterate(initial, i -> i + incremento)
.limit(10)
.collect(Collectors.toList());
}
```

Enquanto nos paradigmas funcional e procedural há o conceito de funções, no orientado a objetos há métodos que manipulam objetos (não necessariamente). Há também grande diferença na quantidade de parâmetros passados às funções/métodos.

Procedural:
```
private static void calculaAparicoes(Integer[] aparicoes, Integer[] barra, Integer[] sequencia, int m) {
for (int i = 0; i < m - 1; i++) {
deslizaControle(aparicoes, barra, sequencia[i] - 1, sequencia[i + 1] - 1);
}
aparicoes[barra[sequencia[m - 2 + 1] - 1]] += 1;
}
```

Orientado a Objetos:
```
public void calculaAparicoes(Dados dados) {
for (int i = 0; i < dados.m - 1; i++) {
deslizaControle(dados, dados.sequencia[i] - 1, dados.sequencia[i + 1] - 1);
}
dados.aparicoes[dados.barra[dados.sequencia[dados.m - 2 + 1] - 1]] += 1;
}
```

No paradigma orientado a objetos ficam claros/intuitivos os passos para a resolução do problema, como pode-se observar no trecho de código abaixo:
```
 Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
int m = sc.nextInt();
Dados dados = new Dados(n, m);
dados.leDados(dados);
Operacoes op = new Operacoes();
op.calculaAparicoes(dados);
dados.imprimeDados(dados);
```
enquanto nos outros paradigmas - funcional e procedural - fica mais difícil a uma pessoa que não conhece o código, por exemplo, perceber a solução sem um olhar mais detalhado.

Observou-se também que nos três paradigmas há estruturas condicionais:

Funcional:
```
return (int) lst.get(0) > (int) lst.get(1) ? (int) lst.get(1) - 1 : (int) lst.get(0);
```
Orientado a Objetos:
```
>if (inicio <= fim) {
[..]
} else {
[..]
}
```

https://stackedit.io/app#
