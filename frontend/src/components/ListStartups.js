import {Table} from 'antd';

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
    }]

function ListStartups({startups}) {
  return (
    <div>
      <Table columns={columns} dataSource={startups} rowKey="name" size="small" pagination={false}/>
    </div>
  );
}

export default ListStartups;