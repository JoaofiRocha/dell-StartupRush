import { useEffect, useState } from "react";
import { Bracket, Seed, SeedItem, SeedTeam } from 'react-brackets';
import { Divider , Button} from 'antd';
import ManageBattle from "../components/ManageBattle.js";
import { useNavigate } from "react-router-dom";

function BracketPage() {
  const navigate = useNavigate();
  const [roundsData, setRoundsData] = useState([]);
  const [selectedBattle, setSelectedBattle] = useState(null);
  const [currentRound , setNextRound] = useState(1);

  const fetchRounds = async () => {
    try {
      const response = await fetch("http://localhost:8080/manageRound");
      const data = await response.json();
      setRoundsData(data);
    } catch (err) {
      alert("Error to fetch rounds");
    }
  };

  const nextRound = async () => {
    try {
      const response = await fetch("http://localhost:8080/nextRound",{
        method: "POST"
      });

      if (!response.ok) {
        throw new Error("Error to advance rounds");
      }

      if (currentRound === roundsData.length) {
        navigate("/end");
        return;
      }

      setNextRound(currentRound + 1);
      fetchRounds();
    } catch (err) {
      alert(err.messege);
    }
  };

  useEffect(() => {
    fetchRounds();
  }, []);

  const handleBattleClose = async () => {
    setSelectedBattle(null);
    await fetchRounds();
  };

  const CustomSeed = ({ seed, breakpoint }) => {
    if (!seed.teams || seed.teams.length < 2) {
      return (
        <Seed>
          <SeedItem style={{ padding: '12px', backgroundColor: 'grey' }}>
            <div>
              <SeedTeam> </SeedTeam>
            </div>
          </SeedItem>
        </Seed>
      );
    }

    const onClick = (battle) => {
      if (battle && !battle.winner) {
        setSelectedBattle(battle)
      }
    }

    const battle = roundsData
      .flatMap(round => round.battles)
      .find(b => b.id === seed.id);

    return (
      <Seed mobileBreakpoint={breakpoint} style={{ fontSize: 15 }}>
        <SeedItem onClick={() => onClick(battle)} style={{ backgroundColor: 'grey' }}>
          <div>
            <SeedTeam>{seed.teams[0]?.name || " "}</SeedTeam>
            <Divider dashed style={{ width: '2px', borderColor: 'white', margin: 0 }} />
            <SeedTeam>{seed.teams[1]?.name || " "}</SeedTeam>
          </div>
        </SeedItem>
      </Seed>
    );
  };

  const buildBracketRounds = () => {
    const completeRounds = [];

    for (let i = 0; i < roundsData.length; i++) {
      const round = roundsData[i];
      let seeds = [];

      if (round.battles.length > 0) {
        seeds = round.battles.map(battle => ({
          id: battle.id,
          teams: battle.startups.map(s => ({ name: s.name }))
        }));
      }
      else if (i > 0) {
        const winners = roundsData[i - 1].battles.map(b => b.winner);

        for (let j = 0; j < winners.length; j += 2) {
          let t1 = { name: " " };
          let t2 = { name: " " };

          if (winners[j]) {
            t1 = { name: winners[j].name };
          }
          if (winners[j + 1]) {
            t2 = { name: winners[j + 1].name };
          }

          seeds.push({
            teams: [t1, t2],
          });
        }
      }
      if (seeds.length === 0) {
        seeds.push({
          id: `placeholder-${i}`,
          teams: [{ name: " " }, { name: " " }],
        });
      }
      completeRounds.push({
        title: `Round ${round.id}`,
        seeds
      });
    }

    return completeRounds;
  };

  const bracketRounds = buildBracketRounds();

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Bracket</h1>
      <Bracket rounds={bracketRounds} renderSeedComponent={CustomSeed} />
      <ManageBattle selectedBattle={selectedBattle} onClose={handleBattleClose} />

      <Button onClick={nextRound}> Advance Round </Button>
    </div>
  );
}

export default BracketPage;

// const rounds = [
//   {
//     title: 'Round one',
//     seeds: [
//       {
//         id: 1,
//         teams: [{ name: 'Team A' }, { name: 'Team B' }],
//       },
//       {
//         id: 2,
//         teams: [{ name: 'Team C' }, { name: 'Team D' }],
//       },
//     ],
//   },
//   {
//     title: 'Final',
//     seeds: [
//       {
//         id: 3,
//         teams: [{ name: 'Team A' }, { name: 'Team C' }],
//       },
//     ],
//   },
// ];

