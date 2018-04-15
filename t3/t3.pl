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


% Q8


% Q9


% Q10
