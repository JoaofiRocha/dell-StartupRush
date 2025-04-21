import { DatePicker, Input, Button, Row, Card } from "antd";
import { useState } from 'react';
import dayjs from 'dayjs';

function RegisterStartupsForm({ onRegister }) {

  const [name, setName] = useState('');
  const [slogan, setSlogan] = useState('');
  const [foundationDate, setFoundationDate] = useState('');


  const register = async (e) => {
    e.preventDefault();

    const payload = {
      name,
      slogan,
      foundationDate
    };

    try {

      const response = await fetch("http://localhost:8080/registerStartups", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      console.log(response.status);

      if (response.status === 500) {
        throw new Error("There's already 8 startups registered");
      }
      else if (!response.ok) throw new Error("Error to register startup");

      onRegister();

    } catch (err) {
      alert(err.message);
    }
  };

  return (
    <Card>
      <form onSubmit={register}>
        <div>
          <label style={{ fontSize: "20px" }}><b>Startup Name</b></label>
          <Input value={name} onChange={(e) => setName(e.target.value)} />
        </div>

        <div style={{ marginTop: "20px" }}>
          <label style={{ fontSize: "20px" }}><b>Slogan</b></label>
          <Input value={slogan} onChange={(e) => setSlogan(e.target.value)} />
        </div>

        <div style={{ marginTop: "20px" }}>
          <Row>
            <label style={{ fontSize: "20px" }}><b>Foundation Year</b></label>
          </Row>
          <Row>
            <DatePicker
              picker="year"
              value={foundationDate ? dayjs(foundationDate, 'YYYY') : null}
              onChange={(e) => {
                if (e) setFoundationDate(e.format('YYYY'));
                else setFoundationDate('');
              }}
            />
          </Row>
        </div>

        <Button onClick={register} style={{ marginTop: "20px" }} >    Register   </Button>
      </form>
    </Card>
  );
}


export default RegisterStartupsForm;