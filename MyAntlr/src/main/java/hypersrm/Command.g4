grammar Command;

stat: rm (expr)? path;

rm: 'rm';

path: PATH;

expr:'-r'                   # r
     | '-rf'                # rf
     | '-f'                 # f
;


PATH: [a-zA-Z]+;
WS: [\t\r\n] +-> skip;



