package fr.ujm.tse.datastructures.proba.linearcouting.model;

/**
 * 
 * A Packet is the atomic value observed here.
 * 
 * @author Christophe Gravier, <christophe.gravier@univ-st-etienne.fr>
 * 
 */
public class Packet {

	private static int H = 26; // max value (starting from 0 included) that the
								// hash function will output
	private String sourceIp = null;
	private String destIp = null;
	private long timestamp = 0;

	public Packet(String sourceIp, String destIp, long timestamp) {
		super();
		this.sourceIp = sourceIp;
		this.destIp = destIp;
		this.timestamp = timestamp;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getDestIp() {
		return destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * This is the hash function used for hashing packet in a linear counter.<br />
	 * It will output a hash value between 0 and <code>this.H</code>. To fastly
	 * generate it I just generate the hashCode method using Eclipse IDE and add
	 * the modulo to stay in the output range, and then renamed the method in
	 * order to keep <code>hashCode()</code> for other operation (like putting
	 * packets into Collections depending on hash codes).
	 * 
	 * @return the hash value of <code>this</code>
	 */
	public int hashPacket() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destIp == null) ? 0 : destIp.hashCode());
		result = prime * result
				+ ((sourceIp == null) ? 0 : sourceIp.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result % H;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destIp == null) ? 0 : destIp.hashCode());
		result = prime * result
				+ ((sourceIp == null) ? 0 : sourceIp.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Packet other = (Packet) obj;
		if (destIp == null) {
			if (other.destIp != null)
				return false;
		} else if (!destIp.equals(other.destIp))
			return false;
		if (sourceIp == null) {
			if (other.sourceIp != null)
				return false;
		} else if (!sourceIp.equals(other.sourceIp))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}

	/**
	 * Get the index of <code>this</code> in the destination IP range.
	 * 
	 * @return the index of <code>this</code> in the destination IP range
	 *         [192.168.1.1, 192.168.1.255].<br />
	 *         For example, if <code>this</code> has a destination IP that is:
	 *         <ul>
	 *         <li>192.168.1.1, the function will return 0</li>
	 *         <li>192.168.1.14, the function will return 13</li>
	 *         <li>192.168.1.255, the function will return 254</li>
	 *         </ul>
	 *         If the destination IP is not parsable, we will return the value
	 *         -1 (We must insert checks in the constructor of the class for IP
	 *         format validation, but this is a toy example, right ?).
	 */
	public int getIpRange() {
		String[] tokens = this.destIp.split("\\.");
		int nodeAddress = Integer.parseInt(tokens[3]);
		return nodeAddress - 1;
	}

	/**
	 * Get the index of <code>this</code> in the time slot of 1 hour range from
	 * April 25th 2014 to May 1st 2014. <br />
	 * A timestamp is the number of elapsed second from January, 1st 1970
	 * midnight UTC.
	 * 
	 * For the starting range of April 25th 2014 00:00:00, the timestamp is:
	 * 1398376800. If we add 3600 seconds to it (1 hour), we have the timestamp
	 * of 25th April 2014 01:00:00. The first time slot is therefore 1398376800
	 * to 1398380400.<br />
	 * 
	 * We recall here that we want to know to which i-th interval
	 * <code>this.timestamp</code> belongs to, starting from i=0 which is
	 * precisely April 25th 2014 00:00:00. In order to have it, we take
	 * <code>this.timestamp</code>, divide it by 3600, and keep the integer
	 * part.
	 * 
	 * @return the index of <code>this</code> in the time slots interval.<br />
	 * 
	 *         If the timestamp is not parsable, we will return thevalue -1 (We
	 *         must insert checks in the constructor of the class for the
	 *         timestamp format validation, but this is a toy example, right ?).
	 */
	public int getTimestampIndex() {
		return (int) Math.floor((double) this.timestamp / 3600);
	}
}
