import React, { useEffect, useState } from "react";
import "./App.css";
import logo from "./logo.svg";

type Greeting = {
  id: number;
  name: string;
};

function App() {
  const [greeting, setGreeting] = useState<Greeting>();
  useEffect(() => {
    fetch("/api")
      .then(res => res.json())
      .then(setGreeting)
      .catch(console.error);
  }, [setGreeting]);
  console.log("kevensnake=>" + JSON.stringify(greeting))

  const[count, setCount] = useState(0);
  function handleClick() {
    setCount(count + 1);
  }


  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div>
          <Game></Game>
        </div>
        {greeting ? (
          <p>Hello5 from {greeting.name} Visit: {greeting.id} </p>
        ) : (
          <p>Loading...</p>
        )}
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>

        <div>
          <MyList />
        </div>

        <div>
          <MapUser></MapUser>
        </div>
      
      <div>
      <h1>Counter that update seperately</h1>
        <MyButton count = {count} onClick = {handleClick} />
        <ul></ul>
        <MyButton count = {count} onClick = {handleClick}/>
      </div>

      </header>
    </div>
  );
}

function MyList() {
  const products = [
    {
      'id': 1,
      'title': 'cabbage',
      'price': 123
    },
    {
      'id': 2,
      'title': 'cucuber',
      'price': 6473
    },
    {
      'id':3,
      'title': 'tomato',
      'price': 846
    }
  ]
  const listItems = products.map(p => 
      <li key = {p.id}>
        {p.title}
        {p.price}
      </li>
  ) 
  return (
    <ul>
      {listItems}
    </ul>
  )
}

interface MyButtonProps {
  count: number;
  onClick: () => void;
}

const MyButton: React.FC<MyButtonProps> = ({count, onClick}) => {
  return (
    <button onClick = {onClick}>you've click {count} times</button>
  );
}

interface SquareProps {
  value: string;
  onClick: () => void;
}

const Square: React.FC<SquareProps> = ({value, onClick}) => {
  return (
    <button 
    className = "square" 
    onClick = {onClick}>
      {value}
      </button>
  )
}


const Board: React.FC<GameProps> =  ({xIsNext, squares, onPlay}) => {
  
  function handleClick(i: number) {
    if (calculateWinner(squares) !== null || squares[i] !== "") {
      alert('already filled or game over!');
      return;
    }
    const nextSquares = squares.slice();
    if(xIsNext) {
      nextSquares[i] = 'X';
    } else {
      nextSquares[i] = 'O';
    }
    onPlay(nextSquares)
  }

  const winner = calculateWinner(squares)

  let status;
  if(winner) {
    status = 'Winner is : ' + winner;
  } else {
    status = 'Next player is : ' + (xIsNext ? 'X' : 'O')
  }
  console.log(status);

  return (
    <>
      <div className="status">{status}</div>
      <div className = "board-row">
        <Square value = {squares[0]} onClick={ () => {handleClick(0)}}/>
        <Square value = {squares[1]} onClick={ () => {handleClick(1)}}/>
        <Square value = {squares[2]} onClick={ () => {handleClick(2)}}/>
      </div>
      <div className = 'board-row'>
        <Square value = {squares[3]} onClick={ () => {handleClick(3)}}/>
        <Square value = {squares[4]} onClick={ () => {handleClick(4)}}/>
        <Square value = {squares[5]} onClick={ () => {handleClick(5)}}/>
      </div>
      <div className='board-row'>
        <Square value = {squares[6]} onClick={ () => {handleClick(6)}}/>
        <Square value = {squares[7]} onClick={ () => {handleClick(7)}}/>
        <Square value = {squares[8]} onClick={ () => {handleClick(8)}}/>
      </div>
    </>
  )
}


function calculateWinner(squares: string[]) {
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6]
  ];
  for (let i = 0; i < lines.length; i++) {
    const [a, b, c] = lines[i];
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
      return squares[a];
    }
  }
  return null;
}

interface GameProps {
  xIsNext: boolean;
  squares: string[];
  onPlay: (squares: string[]) => void;
}
 
function Game() {
  const [history, setHistory] = useState<string[][]>([Array(9).fill('')])
  const [currentMove, setCurrentMove] = useState<number>(0)
  const xIsNext = currentMove % 2 === 0;
  const currentSquares = history[currentMove];
  console.log()

  function handlePlay(nextSquares : string[]) {
    const nextHistory = [...history.slice(0, currentMove + 1), nextSquares]
    setHistory(nextHistory);
    setCurrentMove(nextHistory.length - 1);
  }

  function jumpTo(nextMove : number) {
    setCurrentMove(nextMove);
  }

  const moves = history.map((squares, move) => {
    let desc;
    if(move > 0) {
      desc = 'Go to move #' + move;
    } else {
      desc = 'Go to game start';
    }
    return (
      <li key = {move}>
        <button onClick = {() => {jumpTo(move)}}> {desc} </button>
      </li>
    )
  })
   
  return (
    <div className = "game">
      <div className = "game-board"> 
        <Board xIsNext = {xIsNext} squares = {currentSquares} onPlay = {handlePlay} />
      </div>
      <div className = "game-info">
        <ol>
          {moves}  </ol>
      </div>
    </div>
  )
}


export function MapUser() {
  return (
      <Mapper></Mapper>
  )
}

export function Mapper() {
  const users = [
    'zhang san',
    'li si',
    'wang er mazi'
  ]
  let num = 0;
  const listUsers = users.map( p => <li key = {num}> {p} </li>);
  return (
    <ul>{listUsers}</ul>
  )
}


export default App;
