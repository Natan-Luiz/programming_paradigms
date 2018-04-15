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
    
