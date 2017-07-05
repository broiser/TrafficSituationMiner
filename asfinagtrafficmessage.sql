SELECT *
  FROM asfinagtrafficmessage 
  WHERE road_code like 'A%' AND datex_mse = 'ATOE3' AND datex_phr != 'LS1' AND stateinstanceid IS NOT NULL
  ORDER BY begintime;

 SELECT e.situation_id, a.* 
   FROM situationevolution e
   INNER JOIN stateinstance i ON i.situationevolutionid = e.id
   INNER JOIN asfinagtrafficmessage a ON a.stateinstanceid = i.id
   WHERE e.evosteps > 1 AND e.majorevosteps > 1 AND a.beginmeter > 1;
