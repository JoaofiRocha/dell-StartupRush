import {Table , Card , Button} from 'antd';



function ListStartups({startups , onRemove}) {

  const columns = [
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
      render: text => <a><h4>{text}</h4></a>,
    },
    {
      title: 'Slogan',
      dataIndex: 'slogan',
      key: 'slogan',
    },
    {
      title: 'Foundation Date',
      dataIndex: 'foundationDate',
      key: 'foundationDate',
    },
    {
      title: ' ',
      dataIndex: 'remove',
      key: 'remove',
      render: (_, record) => (
        <Button danger shape="circle" onClick={() => remove(record.id)}>
          <b>X</b>
        </Button>
      )
    }
  ]

  const remove = async (startupId) => {
    try {
      const response = await fetch(`http://localhost:8080/removeStartup/${startupId}`, {
        method: 'POST'
      });
      if(!response.ok) throw new Error("Error to remove startup");
      else onRemove()
    } catch (err) {
      alert(err.message);
    }
    
  };

  return (
    <Card>
      <Table columns={columns} dataSource={startups} rowKey="name" size="small" pagination={false}/>
    </Card>
  );
}

export default ListStartups;