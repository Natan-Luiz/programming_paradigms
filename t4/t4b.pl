%  T4 - Natan Luiz P. Berwaldt
%     
%     Prova OBI 2009 fase 1 - nível 2
%     https://olimpiada.ic.unicamp.br/static/extras/obi2009/provas/ProvaOBI2009_f1i2.pdf
%     
%           Combate à Dengue
% Uma força-tarefa para combater a dengue precisa visitar
% sete casas em busca de focos do mosquito. As casa
% são denominadas F, G, H, L, M, P e T. Deve ser feito
% um plano de acao determinando a ordem em que as
% casas sao visitadas. Para isso considera-se as seguintes
% condi¸coes:
% 
%  - A casa F deve ser uma das tres primeiras a serem
% visitadas.
%  - A casa H deve ser visitada imediatamente antes
% da casa G.
%  - A casa L nao pode ser a primeira nem a setima
% casa a ser visitada.
%  - A casa M deve ser a primeira ou a setima a ser
% visitada.
%  - A casa P deve ser uma das tres ultimas a serem
% visitadas.

regra1(X,Lst) :- 
    nth0(Val,Lst,X), Val < 3.

regra2(X,Y,Lst) :-
    nextto(X,Y,Lst).

regra3(X,Lst) :- 
    nth1(Pos,Lst,X), Pos =\= 7, Pos =\= 1.

regra4(X,Lst) :-
	nth1(Pos,Lst,X), Pos =:= 7; nth1(Pos,Lst,X), Pos =:= 1.

regra5(X,Lst) :- 
    nth0(Val,Lst,X), length(Lst,Len), Val > (Len-3).

solucao(Ordem):-
    Ordem = [_,_,_,_,_,_,_],
    member(f,Ordem),
    member(g,Ordem),
    member(h,Ordem),
    member(l,Ordem),
    member(m,Ordem),
    member(p,Ordem),
    member(t,Ordem),
    regra1(f,Ordem),
    regra2(h,g,Ordem),
    regra3(l,Ordem),
    regra4(m,Ordem),
    regra5(p,Ordem).



/*
Questao 1. Qual das seguintes opcoes e uma lista
completa e correta da ordem que as sete casas devem
ser visitadas?
(A) F, T, H, L, P, G e M.
(B) H, G, F, L, T, P e M.
(C) L, T, F, H G, M e P.
(D) M, F, D, H, L, G e T.
(E) M, L, H, G, F, P e T.


 ?- solucao([f,t,h,l,p,g,m])
 ?- solucao([h,g,f,l,t,p,m])
 ?- solucao([l,t,f,h,g,m,p])
 ?- solucao([m,f,d,h,l,g,t])
 ?- solucao([m,l,h,g,f,p,t])
*/

