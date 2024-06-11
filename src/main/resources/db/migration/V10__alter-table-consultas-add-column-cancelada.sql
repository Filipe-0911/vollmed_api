alter table consultas add column cancelada tinyint;

update consultas set cancelada = 0 where motivo_cancelamento is null;
update consultas set cancelada = 1 where motivo_cancelamento is not null;