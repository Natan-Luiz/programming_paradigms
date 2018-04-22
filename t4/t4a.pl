%
%  T4 - Natan Luiz Paetzhold Berwaldt
%
% 
%
%    CD Independente
%  Uma banda formada por alunos e alunas da escola est´a gravando um CD com exatamente sete m´usicas
% distintas – S, T, V, W, X, Y e Z. Cada m´usica ocupa exatamente uma das sete faixas contidas no
% CD. Algumas das m´usicas s˜ao sucessos antigos de rock; outras s˜ao composi¸c˜oes da pr´opria banda. As
% seguintes restri¸c˜oes devem ser obedecidas:
% - S ocupa a quarta faixa do CD.
% - Tanto W como Y precedem S no CD (ou seja, W e Y est˜ao numa faixa que ´e tocada antes de S
% no CD).
% - T precede W no CD (ou seja, T est´a numa faixa que ´e tocada antes de W).
% - Um sucesso de rock ocupa a sexta faixa do CD.
% - Cada sucesso de rock ´e imediatamente precedido no CD por uma composi¸c˜ao da banda (ou seja,
% no CD cada sucesso de rock toca imediatamente ap´os uma composi¸c˜ao da banda).
% - Z e um sucesso de rock
%

% - Tipo uma declaração dos predicados que terei que usar.
rockSucesso(X).
compBanda(X).

vemAntes(X,Y) :- nth0(R1,CD,w), nth0(R2,CD,y), R1 < R2.

%- Predicado de solucao do problema
solucao(CD):-
    % Cada musica ocupa exatamente uma das sete faixas contidas no CD
    CD = [_,_,_,_,_,_,_],
    
    % adicao dos nomes– S, T, V, W, X, Y e Z
    member(s,CD),
    member(t,CD),
    member(v,CD),
    member(w,CD),
    member(x,CD),
    member(y,CD),
    member(z,CD),
    
    % Tanto W como Y precedem S no CD
    vemAntes(w,s),
    vemAntes(y,s),
    
    % T precede W no CD
    vemAntes(t,w),
    
    % Um sucesso de rock ocupa a sexta faixa 
    nth1(6,CD,C),
    rockSucesso(C),
    
    % Z e um sucesso de rock
    rockSucesso(z),
    
    % Cada sucesso de rock ´e imediatamente precedido no CD por uma composi¸c˜ao da banda
    compBanda(A),
    rockSucesso(B),
    nextto(A,B,CD),
    
    % S ocupa a quarta faixa do CD
    nth1(4,CD,s),.

%Questao 11. Qual das seguintes alternativas poderia
%ser a ordem das musicas no CD, da primeira
%para a setima faixa?
%(A) solucao([t,w,v,s,y,x,z])
%(B) solucao([v,y,t,s,w,z,x])
%(C) solucao([x,y,w,s,t,z,s])
%(D) solucao([y,t,w,s,x,z,v])
%(E) solucao([z,t,x,w,v,y,s])
