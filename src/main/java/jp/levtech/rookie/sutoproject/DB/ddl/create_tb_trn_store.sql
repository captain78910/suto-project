CREATE TABLE tb_trn_store (
    store_latitude DOUBLE PRECISION ,
    store_longitude DOUBLE PRECISION ,
    user_id       VARCHAR(10) ,
    place_id VARCHAR(500) , 
    memo VARCHAR(500) , 
    visit BOOLEAN ,
    evalation int ,
    create_user VARCHAR(10) ,
    create_date TIMESTAMP ,
    update_user VARCHAR(10) ,
    update_date TIMESTAMP ,
    PRIMARY KEY (place_id, user_id )
);

--東京駅
insert into tb_trn_store (store_latitude,store_longitude,user_id,place_id,memo,visit,evalation,create_user,create_date,update_user,update_date)
values (35.6812,139.7671,'user','ChIJ51cu8IcbXWARiRtXIothAS4',NULL,true,3,'user',NOW(),'user',NOW());
--新宿駅
insert into tb_trn_store (store_latitude,store_longitude,user_id,place_id,memo,visit,evalation,create_user,create_date,update_user,update_date)
values (35.6895,139.6917,'user','ChIJH7qx1tCMGGAR1f2s7PGhMhw',NULL,true,3,'user',NOW(),'user',NOW());
--秋葉原駅
insert into tb_trn_store (store_latitude,store_longitude,user_id,place_id,memo,visit,evalation,create_user,create_date,update_user,update_date)
values (35.6995,139.7737,'user','ChIJKTP54qeOGGARsZf1fyU2jxU',NULL,true,3,'user',NOW(),'user',NOW());