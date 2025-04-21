import { Modal, Card, Row, Col, Checkbox } from "antd";
import { Button } from "antd";
import { useState } from "react";

const events = [
  { label: 'Good Pitch', value: 'PITCH' },
  { label: 'Product with Bugs', value: 'BUG' },
  { label: 'Good User Translation', value: 'TRANSLATION' },
  { label: 'Angry Investor', value: 'INVESTOR' },
  { label: 'Shared Fake News', value: 'NEWS' }
];

function BattleManager({ selectedBattle, onClose }) {
  const [winner, setWinner] = useState(null);
  const [winnerModal, setWinnerModal] = useState(false);

  const handleEventChange = (startupId) => async (event) => {
    if (!event) return;
    else if (event.length === 0) {
      event = [];
    }

    console.log(event);
    try {
      const res = await fetch(`http://localhost:8080/manageBattle/${startupId}/setEvent`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(event)
      });


      if (!res.ok) {
        throw new Error(`Error to set event ${event}`);
      }
    } catch (err) {
      alert(err.message);
    }
  }

  const runBattle = async () => {
    try {
      const res = await fetch(`http://localhost:8080/manageBattle/${selectedBattle.id}/run`, {
        method: "POST"
      });
      if (!res.ok) 
        throw new Error("Error to run battle");
      

      if (res.status === 200){
        setWinner(await res.json());
        setWinnerModal(true);
        onClose();
      }

      
    } catch (err) {
      alert(err.message);
    }
  }

  return (
    <>
      <Modal
        open={!!selectedBattle}
        onCancel={onClose}
        footer={null}
        title={`Manage Batle ${selectedBattle?.id}`}>

        <Row justify="space-around">
          {selectedBattle?.startups.map((s) => (

            <Col span={10}>
              <Card title={s.name} >
                Points: {s.points}
              </Card>

              <Card>
                <h4 style={{ margin: "0px" }}>Select Startup Events</h4>
                <Checkbox.Group options={events} onChange={handleEventChange(s.id)} />
              </Card>
            </Col>
          ))}
        </Row>

        <Row justify="end" style={{ marginTop: "20px", marginRight: "50px" }}>
          <Col>
            <Button type="primary" onClick={runBattle}> Run Battle </Button>
          </Col>
        </Row>
      </Modal>

      <Modal
        open={winnerModal}
        onClose={() => setWinnerModal(false)}
        onCancel={() => setWinnerModal(false)}
        footer={null}
      >
        {winner && (
          <div style={{ textAlign: "center" }}>
            <h2>Battle Winner was {winner.name}</h2>
            <p><strong>Slogan:</strong> {winner.slogan}</p>
            <p><strong>Points:</strong> {winner.points}</p>
          </div>
        )}
      </Modal>
    </>
  );
}

export default BattleManager;