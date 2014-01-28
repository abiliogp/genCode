using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsPhonePuzzle
{
	public class PieceUpdateEventArgs : EventArgs
	{
		//Attributes
		public int PieceId{ get; set; }
		public Point NewPosition{ get; set; }

		//Constructor
		public PieceUpdateEventArgs(int PieceId, Point NewPosition)
		{
			this.PieceId = PieceId;
			this.NewPosition = NewPosition;
		}

	}

	public class GameOverEventArgs : EventArgs
	{
		//Attributes
		public int TotalMoves{ get; set; }

		//Constructor
		public GameOverEventArgs(int TotalMoves)
		{
			this.TotalMoves = TotalMoves;
		}

	}

	public class PuzzleState
	{
		//Attributes
		public bool IsPlaying{ get; set; }
		public int ColsAndRows{ get; set; }
		public int[] Board{ get; set; }
		public int TotalMoves{ get; set; }

		//Constructor
		public PuzzleState(bool IsPlaying, int ColsAndRows, int[] Board, int TotalMoves)
		{
			this.IsPlaying = IsPlaying;
			this.ColsAndRows = ColsAndRows;
			this.Board = Board;
			this.TotalMoves = TotalMoves;
		}

	}

	public class PuzzleGame
	{
		//Attributes
		private int colsAndRows;
		private int[] board;
		private int totalMoves;
		private bool isPlaying;
		public EventHandler GameStarted{ get; set; }
		public EventHandler PieceUpdated{ get; set; }
		public EventHandler GameOver{ get; set; }

		//Get and Set
		public int ColsAndRows{ get; set; }
		public int[] Board{ get; set; }
		public int TotalMoves{ get; set; }
		public bool IsPlaying{ get; set; }

		//Constructor
		public PuzzleGame(int colsAndRows, int[] board, int totalMoves, bool isPlaying, EventHandler GameStarted, EventHandler PieceUpdated, EventHandler GameOver)
		{
			this.colsAndRows = colsAndRows;
			this.board = board;
			this.totalMoves = totalMoves;
			this.isPlaying = isPlaying;
			this.GameStarted = GameStarted;
			this.PieceUpdated = PieceUpdated;
			this.GameOver = GameOver;
		}

		//Methods
		public void Reset()
		{
		}

		public void NewGame()
		{
		}

		public int CanMovePiece(int pieceId)
		{
			return Piece;
		}

		public boolean MovePiece(int pieceId)
		{
			return Piece;
		}

		public void CheckWinner()
		{
			// Specified from Sequence Diagram CheckWinner
			for(int n = 0; n < totalPieces - 1; n++)
			{
				if (n != board[n])
				{
				}
			}
			if (completed)
			{
				if (GameOver != null)
				{
					GameOver(this, new GameOverEventArgs { TotalMove = GameOver.this.totalMoves });
				}
			}
		}

		public PuzzleState GetState()
		{
			return State;
		}

		public void SetState(PuzzleState state)
		{
		}

		private void InvokePieceUpdated(int pieceId, Point newPosition)
		{
		}

	}
}