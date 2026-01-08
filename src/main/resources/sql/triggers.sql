DELIMITER //

CREATE TRIGGER tr_atualizar_estoque_ins
AFTER INSERT ON estoque
FOR EACH ROW
BEGIN
    UPDATE produto 
    SET quantidade = quantidade + NEW.quantidade
    WHERE id = NEW.produtoId;
END //

CREATE TRIGGER tr_atualizar_estoque_del
AFTER DELETE ON estoque
FOR EACH ROW
BEGIN
    UPDATE produto 
    SET quantidade = quantidade - OLD.quantidade
    WHERE id = OLD.produtoId;
END //

DELIMITER ;