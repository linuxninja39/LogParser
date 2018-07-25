select
  ip.id        as ipId,
  count(ip.id) as ipCount,
  ip.value     as ipAddress
from
  IpAddress ip
  left outer join
  Log logs
    on ip.id = logs.ipAddress_id
where
  logs.timeStamp between "2017-01-01 13:00:00" and "2017-01-01 14:00:00"
group by
  ip.value
having
  count(ip.id) > 100;

select * from Log log left join IpAddress IA on log.ipAddress_id = IA.id where IA.value = "192.168.185.164";

