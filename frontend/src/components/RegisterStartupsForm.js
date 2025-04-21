import { DatePicker, Input } from "antd";
import { useState } from 'react';
import dayjs from 'dayjs';

function RegisterStartupsForm({onRegister}) {

  const [name, setName] = useState('');
  const [slogan, setSlogan] = useState('');
  const [foundationDate, setFoundationDate] = useState('');


  const handleSubmit = async (e) => {
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
        <form onSubmit={handleSubmit}>
          <label>Startup Name</label>
          <Input value={name} onChange={(e) => setName(e.target.value)} />

          <label>Slogan</label>
          <Input value={slogan} onChange={(e) => setSlogan(e.target.value)} />

          <label>Foundation Year</label>
          <DatePicker
            picker="year"
            value={foundationDate ? dayjs(foundationDate, 'YYYY') : null}
            onChange={(e) => {
              if (e) setFoundationDate(e.format('YYYY'));
              else setFoundationDate('');
            }}
          />

          <button type="submit">Register</button>
        </form>
  );
}


export default RegisterStartupsForm;