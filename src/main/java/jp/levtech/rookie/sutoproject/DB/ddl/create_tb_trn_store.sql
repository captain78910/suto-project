CREATE TABLE tb_tm_store (
    store_latitude DOUBLE PRECISION ,
    store_longitude DOUBLE PRECISION ,
    user_id       VARCHAR(10) ,
    memo VARCHAR(500) , 
    visit BOOLEAN ,
    evalation int ,
    create_user VARCHAR(10) ,
    create_date TIMESTAMP ,
    update_user VARCHAR(10) ,
    update_date TIMESTAMP ,
    PRIMARY KEY (store_latitude, store_longitude, user_id )
);