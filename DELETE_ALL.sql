DELETE FROM transition;
DELETE FROM transitiontype;
DELETE FROM stateinstance;
DELETE FROM discretestateinstance;
DELETE FROM evolvingobject;
DELETE FROM objecttype;
DELETE FROM relationtype;
DELETE FROM situationevolution;
DELETE FROM situationevolutiontype;
DELETE FROM situationevolutiontype_transitiontype;
DELETE FROM situationstatetype;
DELETE FROM situationstatetype_situationevolutiontype;

UPDATE asfinagtrafficmessage SET stateinstanceid = null WHERE stateinstanceid IS NOT NULL;
UPDATE asfinagtrafficmessage SET evolvingobjectid = null WHERE evolvingobjectid IS NOT NULL;
