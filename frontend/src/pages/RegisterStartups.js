import { useEffect, useState } from "react";
import RegisterStartupsForm from "../components/RegisterStartupsForm";
import ListStartups from "../components/ListStartups";
import { Col , Row , Divider, Button} from 'antd';
import { useNavigate } from "react-router-dom";

function RegisterStartups() {
  const [startups, setStartups] = useState([]);
  const navigate = useNavigate();

  const fetchStartups = () => {
    fetch("http://localhost:8080/startups")
      .then((res) => res.json())
      .then((data) => setStartups(data))
      .catch((err) => console.error("Error to Fetch Startups", err));
  };

  useEffect(() => {
    fetchStartups();
  }, []);


  const handleStartCompetition = async () => {
    try {
      const response = await fetch("http://localhost:8080/competition/start", {
        method: "POST",
      });

      if (!response.ok) throw new Error("Error to start the competition");

      navigate("/bracket");
    } catch (err) {
      alert("Error to start the competition");
    }
  };

  return (
    <div>
    <Row justify="space-around">
        <Col span={10}>
            <h1>Register Startup</h1>
            <RegisterStartupsForm onRegister={fetchStartups} />
        </Col>

        <div>
            <Divider type='vertical' variant="dotted" style={{width:'2px', height:'100%'}}> </Divider>
        </div>

        <Col span={10}>
            <h2>Registered Startups</h2>
            <ListStartups startups={startups} />
        </Col>
    </Row>
    
    <Row justify="end" style={{ marginTop: "20px", marginRight: "50px" }}>
        <Col>
            <Button type="primary" onClick={handleStartCompetition}> Start Competition </Button>
        </Col>
    </Row>
    
    </div>
  );
}

export default RegisterStartups;