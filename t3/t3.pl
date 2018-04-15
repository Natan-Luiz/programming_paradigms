% PARADIGMAS DA PROGRAMAÇÃO
% TRABALHO 3
% NATAN LUIZ PAETZHOLD BERWALDT

% Q1

zeroInit(L) :-
    L = [0|_].

% Q2

has5(L):-
    L = [_,_,_,_,_].

% Q3

hasN(L,N) :-
    length(L,N).
    
% Q4

potN0(0,[1]).
potN0(N,L) :-
    L = [H|T],
    N1 is N-1,
    potN0(N1,T),
    pow(2,N,H).

% Q5

zipmult([],[],[]).
zipmult(L1,L2,L3) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H3 is H1*H2,
    zipmult(T1,T2,Z),
    L3 = [H3|Z].

% Q6

potencias(0,[1]).
potencias(N,L):-
    N1 is N-1,
    potencias(N1,Init),
    pow(2,N,Pot),
    Final = [Pot],
    append(Init,Final,L).

% Q7

pos(N,[],Ret):-
    N > 0,
	Ret = [N].
pos(N,Base,Ret):-
    N > 0,
    append([N],Base,Ret).
pos(N,Base,Ret):-
    N =< 0,
    Ret = Base.

positivos([],[]).
positivos(L1,L2):-
    L1 = [H|T],
    positivos(T,T2),
    pos(H,T2,L2).

% Q8
  
mesmaPosicao(_,[],[]).
mesmaPosicao(A,L1,L2):-
    nth0(X,L1,A),
	nth0(X,L2,A).


% Q9

ESSE TROÇO NÃO FAZ SENTIDO NENHUM!!

% Q10

apoio(N,L):-
    floor(sqrt(N),P),
	L is P*P.

azulejos(0, 0).
azulejos(NA, NQ):-
    apoio(NA,L),
    NA2 is NA - L,
    azulejos(NA2,N),
    NQ is N+1.
    
