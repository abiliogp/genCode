using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;

namespace WindowsPhonePuzzle
{
	public partial class PuzzlePage : PhoneApplicationPage
	{
		//Attributes
		private PuzzleGame game;
		private static double DoubleTapSpeed = 500
		private static int ImageSize = 435
		private Canvas[] puzzlePieces;
		private Stream imageStream;
		private long lastTapTicks;
		private int movingPieceId = -1
		private int movingPieceDirection;
		private double movingPieceStartingPosition;

		//Get and Set
		public PuzzleGame Game{ get; set; }
		public double DoubleTapSpeed{ get; set; }
		public int ImageSize{ get; set; }
		public Canvas[] PuzzlePieces{ get; set; }
		public Stream ImageStream{ get; set; }
		public long LastTapTicks{ get; set; }
		public int MovingPieceId{ get; set; }
		public int MovingPieceDirection{ get; set; }
		public double MovingPieceStartingPosition{ get; set; }

		//Constructor
		public PuzzlePage(PuzzleGame game, double DoubleTapSpeed, int ImageSize, Canvas[] puzzlePieces, Stream imageStream, long lastTapTicks, int movingPieceId, int movingPieceDirection, double movingPieceStartingPosition)
		{
			InitializeComponent();
			this.game = game;
			this.DoubleTapSpeed = DoubleTapSpeed;
			this.ImageSize = ImageSize;
			this.puzzlePieces = puzzlePieces;
			this.imageStream = imageStream;
			this.lastTapTicks = lastTapTicks;
			this.movingPieceId = movingPieceId;
			this.movingPieceDirection = movingPieceDirection;
			this.movingPieceStartingPosition = movingPieceStartingPosition;
		}

		//Methods
		private void InitBoard()
		{
		}

		private void AnimatePiece(DependencyObject piece, DependencyProperty dp, double newValue)
		{
		}

		private void PuzzlePiece_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
		{
		}

		private void SolveButton_Click(object sender, RoutedEventArgs e)
		{
		}

		private void PhoneApplicationPage_ManipulationStarted(object sender, ManipulationStartedEventArgs e)
		{
		}

		private void PhoneApplicationPage_ManipulationDelta(object sender, ManipulationDeltaEventArgs e)
		{
		}

		private void PhoneApplicationPage_ManipulationCompleted(object sender, ManipulationCompletedEventArgs e)
		{
		}

	}
}