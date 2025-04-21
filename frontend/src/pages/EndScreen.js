import React, { useState } from 'react';
import { Table } from 'antd';
import { Row, Col, Spin, Card , Divider } from 'antd';
import { useEffect } from 'react';

function EndScreen() {
    const [winner, setWinner] = useState(null);
    const [table, setTable] = useState([]);

    const fetchWinner = async () => {
        try {
            const response = await fetch("http://localhost:8080/competitionWinner");
            const data = await response.json();
            setWinner(data);
            console.log("Winner:", data);
        } catch (err) {
            alert("Error to fetch Winner");
        }
    };

    const fetchTable = async () => {
        try {
            const response = await fetch("http://localhost:8080/getTable");
            const data = await response.json();
            setTable(data);
        } catch (err) {
            alert("Error to fetch Table");
        }
    };


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
            title: 'Points',
            dataIndex: 'points',
            key: 'points',
        }]

    useEffect(() => {
        fetchWinner();
        fetchTable();
    }, []);

    const Load = () => <Spin size="large" />;

    return (
        <div>
            <h1 style={{ margin: "30px", marginTop:"30px" , marginLeft:"110px"}}> Results </h1>
            <Row justify="center" style={{ marginTop: "30px" }}>

                <Col span={10}>
                    <Card>
                        {winner ? (
                            <div>
                                <h1>{winner.name} is the Winner</h1>
                                <h1 style={{ fontSize: "50px" }}>"{winner.slogan}"</h1>
                                <h2>With {winner.points} Points</h2>
                            </div>
                        ) : (
                            <Load />
                        )}
                    </Card>
                </Col>

                <div>
                    <Divider type='vertical' variant="dotted" style={{ width: '2px', height: '100%' }}> </Divider>
                </div>

                <Col span={10}>
                    <Card>
                        <Table columns={columns} dataSource={table} rowKey="name" size="small" pagination={false} />
                    </Card>
                </Col>


            </Row>
        </div>
    );
}


export default EndScreen;