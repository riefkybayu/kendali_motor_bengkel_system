-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2018 at 05:46 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bengkelvisual`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode_barang` varchar(16) NOT NULL,
  `nama_barang` varchar(40) NOT NULL,
  `jumlah` int(4) NOT NULL,
  `modal` int(16) NOT NULL,
  `harga` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `jumlah`, `modal`, `harga`) VALUES
('HND001', 'Ban Dalam Motor Ori Honda', 24, 90000, 115000),
('HND002', 'Ban Luar Ori Motor Honda', 13, 140000, 170000),
('HND003', 'Spion Motor Honda', 27, 30000, 45000),
('HND004', 'Oli Honda', 35, 30000, 40000),
('SZK001', 'Oli Motor Suzuki', 17, 29000, 34000);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `no_trans` int(11) NOT NULL,
  `no_item` int(11) NOT NULL,
  `kode_barang` varchar(16) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `modaltotal` int(16) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`no_trans`, `no_item`, `kode_barang`, `jumlah`, `modaltotal`, `total`) VALUES
(1, 1, 'HND001', 5, 450000, 575000),
(1, 2, 'HND003', 2, 60000, 90000),
(2, 1, 'HND001', 5, 450000, 575000),
(2, 2, 'HND002', 5, 700000, 850000),
(2, 3, 'HND004', 5, 150000, 200000),
(3, 1, 'HND001', 1, 90000, 115000),
(3, 2, 'HND003', 2, 60000, 90000),
(4, 1, 'SZK001', 2, 58000, 68000),
(4, 2, 'HND002', 3, 420000, 510000),
(5, 1, 'HND004', 2, 60000, 80000),
(6, 1, 'HND001', 2, 180000, 230000),
(7, 1, 'HND004', 1, 30000, 40000),
(7, 2, 'HND002', 1, 140000, 170000),
(7, 3, 'HND003', 1, 30000, 45000),
(7, 4, 'HND004', 1, 30000, 40000),
(7, 5, 'SZK001', 1, 29000, 34000),
(8, 1, 'HND001', 2, 180000, 230000),
(8, 2, 'HND002', 3, 420000, 510000),
(9, 1, 'HND004', 1, 30000, 40000),
(9, 2, 'SZK001', 1, 29000, 34000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_trans` int(11) NOT NULL,
  `waktu` date NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_trans`, `waktu`, `total`) VALUES
(1, '2018-04-14', 665000),
(2, '2018-04-14', 1625000),
(3, '2018-04-17', 205000),
(4, '2018-05-31', 578000),
(5, '2018-06-01', 80000),
(6, '2018-06-27', 230000),
(7, '2018-06-28', 404000),
(8, '2018-07-02', 740000),
(9, '2018-07-02', 74000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_trans`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
