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


% Q5

zipmult([],[],[]).
zipmult(L1,L2,L3) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H3 is H1*H2,
    zipmult(T1,T2,Z),
    L3 = [H3|Z].

% Q6


% Q7


% Q8


% Q9


% Q10
