delimiter $$

create procedure findProductsByPrice(
    minPrice decimal(10, 2),
    maxPrice decimal(10, 2)
)
begin
    select id, name, description, price, category_id
        from products
            where price between minPrice and maxPrice
    order by name;
end$$

delimiter ;