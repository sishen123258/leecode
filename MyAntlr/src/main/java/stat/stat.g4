grammar stat;

stat: ID '=' expr;

expr: expr ('+'|'-') expr
    | INT;

ID:[a-zA-Z]+;
INT: [0-9]+;
NEWLINE: '\r' ? '\n';
WS: [\t\r\n] +-> skip;
