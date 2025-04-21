import { useNavigate } from 'react-router-dom';
import { Button } from 'antd';

function Home() {
  const navigate = useNavigate();

  const handleButton = () => navigate('/register');

  return (
    <div className="App">
      <main className='App-header'>
        <p>
          StartupRush
        </p>
        <Button type="primary" onClick={handleButton}>Começar</Button>
      </main>

    </div>
  ); 
}


export default Home;